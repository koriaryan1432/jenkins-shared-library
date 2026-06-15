// vars/buildAndPush.groovy                                                                                                                 
    def call(Map config = [:]) {                                                                                                                
        // Expected config parameters:                                                                                                          
        // - imageName: e.g., 'koriaryan1432/shopease-backend'                                                                                  
        // - buildContext: e.g., './backend'                                                                                                    
        // - buildTag: e.g., env.BUILD_NUMBER                                                                                                   
                                                                                                                                                
        String latestImageName = "${config.imageName}:latest"
        String fullImageName = "${config.imageName}:${config.buildTag}"

        echo "📦 Building Docker image: ${fullImageName}..."
        if (isUnix()) {
            sh "docker pull ${latestImageName} || true"
            sh "docker build --cache-from ${latestImageName} -t ${fullImageName} -t ${latestImageName} ${config.buildContext}"
            echo "🚀 Pushing images to registry..."
            sh "docker push ${fullImageName}"
            sh "docker push ${latestImageName}"
        } else {
            bat "docker pull ${latestImageName} || true"
            bat "docker build --cache-from ${latestImageName} -t ${fullImageName} -t ${latestImageName} ${config.buildContext}"
            echo "🚀 Pushing images to registry..."
            bat "docker push ${fullImageName}"
            bat "docker push ${latestImageName}"
        }                                                                                                                                       
        echo "✅ Image successfully published!"                                                                                                 
    }                
