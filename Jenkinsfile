pipeline {
    agent { label 'develop' }
    stages {
       stage('test') {
       		steps {
       			sh "mvn clean compile test"
       		}
    	}
        stage('Build microservices') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Create docker image) {
        	steps {
        		echo "Creando imagen"
        	}
        }
    }
}