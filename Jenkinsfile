pipeline {
    agent none
    stages {
        stage('test') {
            steps {
                sh "mvn clean compile test"
            }
        }
    }
}