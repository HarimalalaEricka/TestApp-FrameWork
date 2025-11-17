#!/bin/bash

# =====================================
# Variables
# =====================================
PROJECT_DIR=$(pwd)                     # Répertoire courant TestApp
WEB_CONTENT="$PROJECT_DIR/WebContent" # Dossier contenant les JSP et HTML
WEB_INF="$PROJECT_DIR/WEB-INF"        # Dossier WEB-INF
TESTAPP_LIB="$WEB_INF/lib"
FRAMEWORK_JAR="/home/nam/Documents/FrameWork/lib/framework.jar"

TOMCAT_HOME="/opt/tomcat"
WEBAPPS="$TOMCAT_HOME/webapps"
WAR_NAME="TestApp.war"

# =====================================
# Vérifier que WEB-INF existe
# =====================================
mkdir -p "$TESTAPP_LIB"

# =====================================
# Copier le JAR du framework
# =====================================
cp -f "$FRAMEWORK_JAR" "$TESTAPP_LIB/"
if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la copie du framework.jar!"
    exit 1
fi
echo "✅ framework.jar copié dans TestApp/WEB-INF/lib avec succès"

# =====================================
# Créer le WAR correctement
# =====================================
rm -f "$WAR_NAME"

# Inclure WebContent et WEB-INF (avec web.xml)
jar cvf "$WAR_NAME" \
    -C "$WEB_CONTENT" . \
    -C "$WEB_INF" .

if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la création du WAR!"
    exit 1
fi

echo "✅ WAR créé avec succès : $WAR_NAME"

# =====================================
# Déployer dans Tomcat
# =====================================
rm -f "$WEBAPPS/$WAR_NAME"
cp -f "$WAR_NAME" "$WEBAPPS/"

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
