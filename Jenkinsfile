pipeline {
    agent any
 
    environment {
        DOCKER_IMAGE = "santrajrakebat/employee-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }
 
    tools {
        maven 'MAVEN_HOME'
    }
 
    stages {
        stage("Git Clone") {
            steps {
                git branch: "main", url: "https://github.com/rakebatsantraj/EmployeeManagementService.git"
                bat 'ls -al'
                bat 'git status'
            }
        }
 
        stage("Build App") {
            steps {
                bat "mvn clean install -DskipTests"
            }
        }
 
        stage("Build Docker Image") {
            steps {
                bat "docker build -t ${DOCKER_IMAGE}:${IMAGE_TAG} ."
            }
        }
 
        stage("Pubat Image to DockerHub") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Docker-Hub-credential', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker pubat ${DOCKER_IMAGE}:${IMAGE_TAG}
                    """
                }
            }
        }
 
        stage("Deploy to Kubernetes") {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    bat "kubectl apply -f deploy.yml"
                    bat "kubectl apply -f service.yml"
                }
            }
        }
    }
}