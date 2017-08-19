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
                            credentialsId: '40318e92-1e82-48b0-adbb-21b8219345bf',
                            usernameVariable: 'USERNAME',
                            passwordVariable: 'PASSWORD']]) {
                    sh 'docker login -u $USERNAME -p $PASSWORD'
                    sh 'docker push ealberto/college-app-tracker'
                 }
            }
        }
    }
}