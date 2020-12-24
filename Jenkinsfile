pipeline {
    agent any
    environment {
    	registry = "jesusmatosp/microservice-cliente-intercorp"
    	registryCredentials = 'docker-jmp-cred'
    	dockerImage = ''
    	TAG_MICROSERVICE_CLIENTE = '23'
    }
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
        
        stage ('Building Image') {
        	steps {
        		script {
        			dockerImage = docker.build registry + "latest"
        		}
        	}
        }
        
        stage ('Deploy Image') {
        	steps {
        		script {
        			docker.withRegistry( '', registryCredentials) {
        				dockerImage.push()
        			}
        		}
        	}
        }
        
        stage ('Remove unused docker image') {
        	steps {
        		sh "docker rmi $registry:latest"
        	}
        }
        
    }
}