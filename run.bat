set game_home=C:\Code\Ballmaster-Game
set java_bin=C:\Code\jdk-24.0.1\bin
set jar_game=%game_home%\game.jar

REM run
%java_bin%\java -jar %jar_game% %*
