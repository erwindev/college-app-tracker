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

        stage ('Docker Build'){
            steps{
                sh 'docker build -t ealberto/college-app-tracker .'
            }
        }

        stage ('Docker Tag'){
            steps{
                sh 'docker tag ealberto/college-app-tracker ealberto/college-app-tracker:latest'
            }
        }

        stage ('Docker Push'){
            steps{
                 withCredentials([[$class: 'UsernamePasswordMultiBinding',
                            credentialsId: '947617d1-a6a0-4af0-8098-27b976ae7486',
                            usernameVariable: 'USERNAME',
                            passwordVariable: 'PASSWORD']]) {
                    sh 'docker login -u $USERNAME -p $PASSWORD'
                    sh 'docker push ealberto/college-app-tracker'
                 }
            }
        }
    }
}