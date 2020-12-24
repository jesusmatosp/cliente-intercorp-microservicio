pipeline {
    agent any
    environment {
    	registry = "jesusmatosp/microservice-cliente-intercorp"
    	registryCredentials = 'docker-jmp-cred'
    	dockerImage = ''
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
        			dockerImage = docker.build registry + ":$BUILD_NUMBER"
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
        		sh "docker rmi $registry:$BUILD_NUMBER"
        	}
        }
        
        stage ('Deploy') {
	        steps {
	            sh 'scp /home/intercorp.pem deploy.sh ${REMOTE_USER}@${REMOTE_HOST}:~/'
	            sh 'ssh /home/intercorp.pem ${REMOTE_USER}@${REMOTE_HOST} "chmod +x deploy.sh"'
	            sh 'ssh /home/intercorp.pem ${REMOTE_USER}@${REMOTE_HOST} ./deploy.ssh $BUILD_NUMBER'
	        }
	    }
    }
}