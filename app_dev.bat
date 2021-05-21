@ECHO OFF
@ECHO START

PAUSE

TIMEOUT 60

CD /d "C:\Program Files\Docker\Docker\"
START /min "" ".\Docker Desktop.exe"

cd /d "D:\Workspace\sts-4.10.0.RELEASE\" & start /min "" ".\SpringToolSuite4.exe"

cd /d "D:\Workspace\Github\usj_pi2_projeto_final\.FINAL\projeto-integrador-ii\web" && CODE . 

