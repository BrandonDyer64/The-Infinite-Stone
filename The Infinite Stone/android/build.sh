#Build
echo 'Building...'
'/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/gradlew' assembleRelease --stacktrace

#Sign
echo 'Signing...'
jarsigner -verbose -storepass 'Chip0100' -sigalg SHA1withRSA -digestalg SHA1 -keystore '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/key.keystore' '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/android/build/outputs/apk/android-release-unsigned.apk' 'The Infinite Stone'

#Align
echo 'Aligning...'
cd '/home/caninelizard/Documents/android-sdk-linux/build-tools/23.0.1'
./zipalign -f -v 4 '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/android/build/outputs/apk/android-release-unsigned.apk' '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/android/build/outputs/apk/outfile.apk'
echo 'Installing...'
#adb -d install '/home/caninelizard/git/The-Infinite-Stone/The Infinite Stone/android/build/outputs/apk/outfile.apk'
