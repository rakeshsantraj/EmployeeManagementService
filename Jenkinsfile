pipeline {
    agent any
 
    environment {
        DOCKER_IMAGE = "santrajrakesh/employee-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
        KUBECONFIG = 'C:\\Users\\rakes\\.kube\\config'
    }
 
    tools {
        maven 'MAVEN_HOME'
    }
 
    stages {
        stage("Git Clone") {
            steps {
                git branch: "main", url: "https://github.com/rakeshsantraj/EmployeeManagementService.git"
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
                bat "docker build -t ${DOCKER_IMAGE}:latest ."
            }
        }
 
        stage("Push Image to DockerHub") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Docker-Hub-credential', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push ${DOCKER_IMAGE}:latest
                    """
                }
            }
        }
 
        stage("Deploy to Kubernetes") {
            steps {
            		bat 'kubectl config use-context docker-desktop'
            		bat 'kubectl apply -f mysql-deployment.yml'
            		bat 'kubectl apply -f mysql-service.yml'
                    bat "kubectl apply -f deploy.yml"
                    bat "kubectl apply -f service.yml"
            }
        }
    }
}