game_home="/Users/stallion/Code/Ballmaster-Game"
java_bin="$game_home/runtime/jdk-24.0.1.jdk/Contents/Home/bin"
java_out="$game_home/tmp"
java_src="$game_home/src/com/app/game/*.java"
jar_game="$game_home/editor.jar"
jar_engine="$game_home/engine.jar"
manifest="$game_home/debug/MANIFEST_EDITOR.MF"


#prep() {
#  rm -f $jar_game
#  rm -rf $java_out
#}

build() {
  $java_bin/javac -cp .:$jar_engine -d $java_out $java_src
  $java_bin/jar cmf $manifest $jar_game -C $java_out .
}

cleanup() {
  rm -rf $java_out
}

#prep
build
cleanup
