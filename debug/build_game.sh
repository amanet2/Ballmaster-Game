game_home="/Users/stallion/Code/Ballmaster-Game"
java_bin="$game_home/runtime/jdk-23.0.2.jdk/Contents/Home/bin"
java_out="$game_home/tmp"
java_src="$game_home/src/com/app/game/*.java"
jar_game="$game_home/game.jar"
jar_engine="$game_home/engine.jar"


prep() {
  rm -f $jar_game
  rm -rf $java_out
}

build() {
  $java_bin/javac -d $java_out $java_src
  $java_bin/jar cf $jar_name -C $java_out .
}

cleanup() {
  rm -rf $java_out
  rm -f $jar_copy_to/$jar_name
  cp $jar_name $jar_copy_to
}

prep
build
cleanup
