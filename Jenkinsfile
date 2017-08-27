pipeline {
    agent any
    stages {
        def dockerImage
        def app

        stage('Clone repository') {
            checkout scm
        }

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

        stage ('Docker Build'){
            steps{
                dockerImage = docker.build('ealberto/college-app-tracker')
            }
        }

        stage ('Docker Push'){
            steps{
                docker.withRegistry('https://registry.hub.docker.com', 'ealberto-docker-hub'){
                    dockerImage.push('latest')
                }
            }
        }
    }
}