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
            def dockerImage
            def app
            steps{
                dockerImage = docker.build('ealberto/college-app-tracker')
            }
            steps{
                docker.withRegistry('https://registry.hub.docker.com', 'ealberto-docker-hub'){
                    dockerImage.push('latest')
                }
            }
        }
    }
}