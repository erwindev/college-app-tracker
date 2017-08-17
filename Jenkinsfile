https://github.com/erwindev/college-app-tracker.git

pipeline {
    agent any

    stages {
        stage ('Compile'){
            steps{
                sh 'gradlew compilejava'
            }
        }

        stage ('Run Tests'){
            steps{
                sh 'gradlew test'
            }
        }

        stage ('Create Jar File'){
            steps{
                sh 'gradlew assemble'
            }
        }
    }
}