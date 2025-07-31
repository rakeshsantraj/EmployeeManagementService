pipeline {
    agent any
 
    environment {
        DOCKER_IMAGE = "santrajrakesh/employee-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }
 
    tools {
        maven 'MAVEN_HOME'
    }
 
    stages {
        stage("Git Clone") {
            steps {
                git branch: "main", url: "https://github.com/rakeshsantraj/EmployeeManagementService.git"
                sh 'ls -al'
                sh 'git status'
            }
        }
 
        stage("Build App") {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }
 
        stage("Build Docker Image") {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${IMAGE_TAG} ."
            }
        }
 
        stage("Push Image to DockerHub") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Docker-Hub-credential', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push ${DOCKER_IMAGE}:${IMAGE_TAG}
                    """
                }
            }
        }
 
        stage("Deploy to Kubernetes") {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    sh "kubectl apply -f deploy.yml"
                    sh "kubectl apply -f service.yml"
                }
            }
        }
    }
}