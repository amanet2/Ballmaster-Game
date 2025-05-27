set game_home=C:\Code\Ballmaster-Game
set java_bin=C:\Code\jdk-24.0.1\bin
set java_out=%game_home%\tmp
set java_src=%game_home%\src\com\app\game\*.java
set jar_game=%game_home%\game.jar
set jar_engine=%game_home%\engine.jar
set manifest=%game_home%\MANIFEST.MF

REM build
REM on macOS classpath is separated by '.' on windows it's ';'
%java_bin%\javac -cp .;%jar_engine% -d %java_out% %java_src%
%java_bin%\jar cmf %manifest% %jar_game% -C %java_out% .

REM cleanup
rmdir /s /q %java_out%
