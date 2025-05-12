game_home="/Users/stallion/Code/Ballmaster-Game"
java_bin="$game_home/runtime/jdk-23.0.2.jdk/Contents/Home/bin"
jar_game="$game_home/game.jar"

run() {
  $java_bin/java -jar $jar_game "$@"
}

run "$@" # important to pass args to run function
