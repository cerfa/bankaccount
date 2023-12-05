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
