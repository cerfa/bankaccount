pipeline {
    agent none
    stages {
		  stage('checkout') {
            agent any
            steps {
                sh 'git checkout main'
            }
        }
	     stage('clean') {
            agent any
            steps {
                sh 'mvn clean'
                sh 'mvn sonar:sonar -Dsonar.projectKey=bankaccount -Dsonar.host.url=http://localhost:9000 -Dsonar.login=ee177918609ad27dcbbe85de40f010e27c6c4405'
            }
        }
        stage('validate') {
            agent any
            steps {
                sh 'mvn validate'
            }
        }
        stage('compile') {
            agent any
            steps {
                sh 'mvn compile'
            }
        }
        stage('package') {
            agent any
            steps {
                sh 'mvn package -DskipTests=true'
               }
            }
        stage('install') {
            agent any
            steps {
                sh 'mvn install -DskipTests=true'
	       }
           			
            post {
               success { 
                    sh 'echo done'
                }
		}
	}	
      }
    }
