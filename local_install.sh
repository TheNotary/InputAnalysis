#!/usr/bin/env bash

set -e

app_dest=target/InputAnalysis
jar_path=target/InputAnalysis-0.0.0-SNAPSHOT.jar

echo "#!/usr/bin/java -jar" > "${app_dest}" && \
  cat ${jar_path} >> "${app_dest}" && \
  chmod 0755 ${app_dest}


echo "$(date): I just built" >> debug.log

