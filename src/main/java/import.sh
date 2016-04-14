javah logic.imagematching.features.pixelbypixel.ImageMatcher 
gcc -v -c -fPIC -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers/  ImageMatcher.cpp -o ImageMatcher.o
libtool -dynamic -lSystem ImageMatcher.o -o libImageMatcher.dylib
cp libImageMatcher.dylib ../../../lib/libImageMatcher.dylib
