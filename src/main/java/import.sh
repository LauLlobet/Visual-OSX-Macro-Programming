javah logic.imagematching.features.pixelbypixel.ImageMatcher 
gcc -v -c -fPIC -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers/  ImageMatcher.cpp -o ImageMatcher.o
gcc -v -c -fPIC -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers/  ImageMatcherHelper.cpp -o ImageMatcherHelper.o

libtool -dynamic -lSystem ImageMatcher.o ImageMatcherHelper.o  -o libImageMatcher.dylib
cp libImageMatcher.dylib ../../../lib/libImageMatcher.dylib
