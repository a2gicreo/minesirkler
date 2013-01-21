
set datoStreng = %date:~6,10%_%date:~3,3%%date:~0,2%

mkdir c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%
mkdir c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\drawable
mkdir c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\layout
mkdir c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\menu
mkdir c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\values

14.01.2013

xcopy "MineSirklerMob\src\molas\minesirklermobil" /e/d/y  "c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\"

xcopy "M:\Git_TestProsjekt2\MineSirklerMob\res\drawable" /e/d/y  "c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\drawable"
   
xcopy "M:\Git_TestProsjekt2\MineSirklerMob\res\layout" /e/d/y  "c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\layout"

xcopy "M:\Git_TestProsjekt2\MineSirklerMob\res\menu" /e/d/y  "c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\menu"

xcopy "M:\Git_TestProsjekt2\MineSirklerMob\res\values" /e/d/y  "c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%\values"

xcopy "M:\Git_TestProsjekt2\MineSirklerMob\AndroidManifest.xml" /y  "c:\MSBackup\%date:~6,10%_%date:~3,3%%date:~0,2%"



pause

