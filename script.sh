#!/bin/bash

# =====================================
# Variables
# =====================================
TESTAPP_LIB="./WEB-INF/lib"
FRAMEWORK_PATH="../Framework"
FRAMEWORK_JAR="$FRAMEWORK_PATH/lib/framework.jar"

TOMCAT_HOME="/opt/tomcat"
WEBAPPS="$TOMCAT_HOME/webapps"
WAR_NAME="TestApp.war"

# =====================================
# Copier le JAR du framework dans TestApp
# =====================================
mkdir -p "$TESTAPP_LIB"

cp -f "$FRAMEWORK_JAR" "$TESTAPP_LIB/"
if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la copie du framework.jar!"
    read -p "Appuyez sur Entrée pour quitter..."
    exit 1
fi

echo "✅ framework.jar copié dans TestApp/WEB-INF/lib avec succès"

# =====================================
# Créer le WAR
# =====================================
if [ -f "$WAR_NAME" ]; then
    rm -f "$WAR_NAME"
fi

jar cvf "$WAR_NAME" -C WebContent . -C . WEB-INF
if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la création du WAR!"
    read -p "Appuyez sur Entrée pour quitter..."
    exit 1
fi

echo "✅ WAR créé avec succès : $WAR_NAME"

# =====================================
# Déployer dans Tomcat
# =====================================
if [ -f "$WEBAPPS/$WAR_NAME" ]; then
    rm -f "$WEBAPPS/$WAR_NAME"
fi

cp -f "$WAR_NAME" "$WEBAPPS/"
if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la copie du WAR dans Tomcat!"
    read -p "Appuyez sur Entrée pour quitter..."
    exit 1
fi

echo "✅ Application $WAR_NAME déployée dans Tomcat/webapps"

# =====================================
# Redémarrer Tomcat
# =====================================
echo "🔄 Redémarrage de Tomcat..."
"$TOMCAT_HOME/bin/shutdown.sh"
sleep 3
"$TOMCAT_HOME/bin/startup.sh"

echo "====================================="
echo "🚀 Déploiement terminé!"
echo "URL: http://localhost:8080/TestApp"
echo "====================================="
read -p "Appuyez sur Entrée pour terminer..."
