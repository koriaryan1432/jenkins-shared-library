// vars/dockerLogin.groovy
def call(String credentialsId) {
    echo 'Requesting Docker Hub credentials from Jenkins vault...'
    
    // This securely grabs the credentials from Jenkins and masks them in the logs
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
        if (isUnix()) {
            sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
        } else {
            bat 'echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin'
        }
        echo 'Successfully logged into Docker Hub!'
    }
}