#!/bin/bash
cd src
javac -g:none -target 1.7 *.java
jar cvmf META-INF/MANIFEST.MF DNA_analy.jar *.class
rm *.class
mv DNA_analy.jar ..
cd ..
if [ -f DNA_analy.sh ]; then rm DNA_analy.sh; fi
echo -e "#!/bin/bash\njava -jar DNA_analy.jar" >> DNA_analy.sh
chmod +x DNA_analy.sh