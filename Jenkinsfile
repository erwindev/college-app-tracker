pipeline {
    agent any

    def app

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
                app = docker.build('ealberto/college-app-tracker')
            }
        }

        stage ('Docker Push'){
            steps{
                 withCredentials([[$class: 'UsernamePasswordMultiBinding',
                            credentialsId: '40318e92-1e82-48b0-adbb-21b8219345bf',
                            usernameVariable: 'USERNAME',
                            passwordVariable: 'PASSWORD']]) {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        app.push("latest")
                    }
                 }
            }
        }
    }
}