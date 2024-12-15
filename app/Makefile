run-dist:
	./build/install/app/bin/app -h

clean:
	./gradlew clean

build:
	./gradlew clean build

install-dist:
	./gradlew clean installDist

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

test-files:
	./build/install/app/bin/app file1.json file2.json

test-files-2: 
	./build/install/app/bin/app file3.json file4.json

.PHONY: build clean lint config test



