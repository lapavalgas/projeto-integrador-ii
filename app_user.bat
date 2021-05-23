CD /d "C:\Program Files\Docker\Docker\"
START /min "" ".\Docker Desktop.exe"

TIMEOUT 40

start cmd.exe /c docker-compose up
start "" "http://localhost:8082/"