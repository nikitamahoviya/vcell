#!/usr/bin/env bash

#
# build_installers.sh must be run within Docker (see Dockerfile and docker-compose.yml in /vcell/docker/installers)
# 

shopt -s -o nounset

# NOT_USED (replace with install4j 'scan dir' functionality in I4j launcher config) gather classpath (filenames only), Install4J will add the correct separator
#compiler_vcellClasspathColonSep=`ls -m /vcellclient/vcell-client/target/maven-jars | tr -d '[:space:]' | tr ',' ':'`

cd /config

PATH=/installer/install4j8.0.5/bin:$PATH

#
# retrieve text secrets from file (docker-compose 'secrets' mounts these files in memory at runtime)
#
Install4J_product_key=`cat $Install4J_product_key_file`
winCodeSignKeystore_pswd=`cat $winCodeSignKeystore_pswdfile`
macCodeSignKeystore_pswd=`cat $macCodeSignKeystore_pswdfile`

#
# install Install4J product key (only really needed first time when running this Docker container)
#
/installer/install4j8.0.5/bin/install4jc -L $Install4J_product_key

#echo "jres found in /jres are `ls /jres`"
#cp /jres/* /config
#echo "jres found in /config are `ls /config`"
# if [ -e /config/macosx-amd64-1.8.0_66.tar.gz ] ; then
# 	echo
# 	echo "found /config/macosx-amd64-1.8.0_66.tar.gz"
# 	echo
# else
# 	echo
# 	echo "could not find /config/macosx-amd64-1.8.0_66.tar.gz"
# 	echo
# fi	

# cd /jres
#       - macJre=macosx-amd64-1.8.0_141
#       - win64Jre=windows-amd64-1.8.0_141
#       - win32Jre=windows-x86-1.8.0_141


#
# run install4jc to create installers for VCell Client on supported platforms.
#
#Separate build of win, linux and mac installers to avoid random failure due to threading

#Generate Windows installers
/installer/install4j8.0.5/bin/install4jc \
	-m windows \
	--win-keystore-password=$winCodeSignKeystore_pswd \
	--mac-keystore-password=$macCodeSignKeystore_pswd \
	-D \
vcellIcnsFile=/config/icons/vcell.icns,\
outputDir=/outputdir,\
mavenRootDir=/vcellclient,\
macJrePath=/jres/$macJre,\
win64JrePath=/jres/$win64Jre,\
win32JrePath=/jres/$win32Jre,\
linux64JrePath=/jres/$linux64Jre,\
linux32JrePath=/jres/$linux32Jre,\
macKeystore=$macCodeSignKeystore_p12,\
winKeystore=$winCodeSignKeystore_pfx,\
applicationId=$compiler_applicationId,\
SoftwareVersionString=$compiler_softwareVersionString,\
Site=$compiler_Site,\
vcellVersion=$compiler_vcellVersion,\
vcellBuild=$compiler_vcellBuild,\
updateSiteBaseUrl=$compiler_updateSiteBaseUrl,\
rmiHosts=$compiler_rmiHosts,\
bioformatsJarFile=$compiler_bioformatsJarFile,\
bioformatsJarDownloadURL=$compiler_bioformatsJarDownloadURL\
	VCell.install4j
	
mv /outputdir/updates.xml /outputdir/updates_win.xml

#Generate linux installers
/installer/install4j8.0.5/bin/install4jc \
	-m unixInstaller \
	--win-keystore-password=$winCodeSignKeystore_pswd \
	--mac-keystore-password=$macCodeSignKeystore_pswd \
	-D \
vcellIcnsFile=/config/icons/vcell.icns,\
outputDir=/outputdir,\
mavenRootDir=/vcellclient,\
macJrePath=/jres/$macJre,\
win64JrePath=/jres/$win64Jre,\
win32JrePath=/jres/$win32Jre,\
linux64JrePath=/jres/$linux64Jre,\
linux32JrePath=/jres/$linux32Jre,\
macKeystore=$macCodeSignKeystore_p12,\
winKeystore=$winCodeSignKeystore_pfx,\
applicationId=$compiler_applicationId,\
SoftwareVersionString=$compiler_softwareVersionString,\
Site=$compiler_Site,\
vcellVersion=$compiler_vcellVersion,\
vcellBuild=$compiler_vcellBuild,\
updateSiteBaseUrl=$compiler_updateSiteBaseUrl,\
rmiHosts=$compiler_rmiHosts,\
bioformatsJarFile=$compiler_bioformatsJarFile,\
bioformatsJarDownloadURL=$compiler_bioformatsJarDownloadURL\
	VCell.install4j

mv /outputdir/updates.xml /outputdir/updates_linux.xml


#Generate mac installer
/installer/install4j8.0.5/bin/install4jc \
	-m macos \
	--win-keystore-password=$winCodeSignKeystore_pswd \
	--mac-keystore-password=$macCodeSignKeystore_pswd \
	-D \
vcellIcnsFile=/config/icons/vcell.icns,\
outputDir=/outputdir,\
mavenRootDir=/vcellclient,\
macJrePath=/jres/$macJre,\
win64JrePath=/jres/$win64Jre,\
win32JrePath=/jres/$win32Jre,\
linux64JrePath=/jres/$linux64Jre,\
linux32JrePath=/jres/$linux32Jre,\
macKeystore=$macCodeSignKeystore_p12,\
winKeystore=$winCodeSignKeystore_pfx,\
applicationId=$compiler_applicationId,\
SoftwareVersionString=$compiler_softwareVersionString,\
Site=$compiler_Site,\
vcellVersion=$compiler_vcellVersion,\
vcellBuild=$compiler_vcellBuild,\
updateSiteBaseUrl=$compiler_updateSiteBaseUrl,\
rmiHosts=$compiler_rmiHosts,\
bioformatsJarFile=$compiler_bioformatsJarFile,\
bioformatsJarDownloadURL=$compiler_bioformatsJarDownloadURL\
	VCell.install4j

mv /outputdir/updates.xml /outputdir/updates_mac.xml


#reconstruct combined updates.xml from fragments (used by VCell client executable to detect if update needed)
winc=$(wc -l < /outputdir/updates_win.xml)
linuxc=$(wc -l < /outputdir/updates_linux.xml)
macc=$(wc -l < /outputdir/updates_mac.xml)

sed -n -e "1,$(($winc-1))p" /outputdir/updates_win.xml >/outputdir/updates.xml
sed -n -e "3,$(($linuxc-1))p" /outputdir/updates_linux.xml >>/outputdir/updates.xml
sed -n -e "3,$(($macc))p" /outputdir/updates_mac.xml >>/outputdir/updates.xml

