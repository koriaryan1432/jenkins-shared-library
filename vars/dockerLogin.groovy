// vars/dockerLogin.groovy
def call(String credentialsId) {
    echo 'Requesting Docker Hub credentials from Jenkins vault...'
    
    // This securely grabs the credentials from Jenkins and masks them in the logs
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        
        // Executes the native Windows batch command to log into Docker
        bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
        echo 'Successfully logged into Docker Hub!'
    }
}