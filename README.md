# bankaccount
Simple bank account management

# Sonar Step in jenkins
stage('sonar') { 
agent any 
steps { 
sh 'mvn verify sonar:sonar -Dsonar.projectKey=bankaccount -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_b402930b4b7e4d744110c162ae823ee5baf105fe' 
} 
}