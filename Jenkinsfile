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
                def dockerImage = docker.build('ealberto/college-app-tracker')
            }
        }
    }
}