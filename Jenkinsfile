pipeline {
    agent any
    tools { 
        maven 'maven_jenkins' 
        jdk 'java_jenkins' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Test With Maven') {
            steps {
                sh "mvn clean compile test"
            }
        }
    }
}