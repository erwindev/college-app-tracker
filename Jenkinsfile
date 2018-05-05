pipeline {
    agent any
    stages {
        stage ('Compile'){
            steps{
                sh './gradlew compilejava'
            }
        }

        stage ('Run Tests'){
            steps{
                sh './gradlew test'
            }
        }

        stage ('Create Jar File'){
            steps{
                sh './gradlew assemble'
            }
        }

        stage ('Docker Build and Push'){
            steps{
                script{
                    def dockerImage = docker.build('ealberto/college-app-tracker')
                    docker.withRegistry('https://registry.hub.docker.com', 'ealberto-docker-hub'){
                        dockerImage.push('latest')
                    }
                }
            }
        }
    }
}