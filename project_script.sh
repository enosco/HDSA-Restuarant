makepdf() { more "$@"  | enscript -Ejava -b`whoami` -i4 -2rG -o - | ps2pdf - `whoami`Submission.pdf;}
astyle --mode=java *.java
javac *.java
java Driver <~hristescu/public_html/classes/dsa/restaurant_input.txt >ProjectSampleruns.txt 2>>ProjectSampleruns.txt
more ProjectStatus.txt ProjectConclusions.txt project_script.sh Driver.java  >>allfiles.txt
# uncomment and update the line below to incorporate all supporting classes you have developed
SeatedParty.java Party.java Table.java Name.java Section.java RemoveOnOccupiedTableException.java >>allfiles.txt

java ECDriver < ECin.txt > ECProjectSampleruns.txt
more ECDriver.java >> allfiles.txt
ECSeatedParty.java ECSection.java ECTable.java Seats.java >> allfiles.txt

more ProjectSampleruns.txt >>allfiles.txt
more ECProjectSampleruns.txt >>allfiles.txt
makepdf allfiles.txt
