#cd '/home/git/The-Infinite-Stone/The Infinite Stone/android'
'/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/gradlew' assembleRelease --stacktrace
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/key.keystore' '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/android/build/outputs/apk/android-release-unsigned.apk' 'The Infinite Stone'
cd '/home/caninelizard/Documents/android-sdk-linux/build-tools/23.0.1'
./zipalign -f -v 4 '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/android/build/outputs/apk/android-release-unsigned.apk' '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/android/build/outputs/apk/outfile.apk'
