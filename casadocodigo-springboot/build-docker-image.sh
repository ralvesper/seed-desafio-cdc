#!/bin/bash

# Verifica se o pom.xml existe
if [[ ! -f "pom.xml" ]]; then
    echo "Erro: pom.xml não encontrado no diretório atual."
    exit 1
fi

# Extraindo <name> diretamente do pom.xml e removendo espaços extras
NAME=$(grep -oPm1 "(?<=<name>)[^<]+" pom.xml | head -1 | xargs)

# Extraindo a versão correta da aplicação e removendo espaços extras
VERSION=$(awk '
    /<parent>/ { parent = 1 }
    /<\/parent>/ { parent = 0 }
    parent == 0 && /<version>/ { gsub(/<version>|<\/version>/, ""); print; exit }
' pom.xml | xargs)

# Valida se os valores foram encontrados
if [[ -z "$NAME" || -z "$VERSION" ]]; then
    echo "Erro: Não foi possível extrair name ou version do pom.xml."
    exit 1
fi

# Nome da imagem Docker no formato especificado
IMAGE_NAME="ralvesper/mmv3-${NAME}:${VERSION}"

echo "Construindo a imagem Docker: $IMAGE_NAME"

# Construir a imagem Docker
docker build -t "$IMAGE_NAME" .

if [[ $? -eq 0 ]]; then
    echo "Imagem Docker criada com sucesso: $IMAGE_NAME"
else
    echo "Erro ao construir a imagem Docker."
    exit 1
fi
