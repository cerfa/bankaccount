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
            }
        }
	     stage('sonar') {
            agent any
            steps {
                sh 'mvn verify sonar:sonar -Dsonar.projectKey=bankaccount -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_b402930b4b7e4d744110c162ae823ee5baf105fe'
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
