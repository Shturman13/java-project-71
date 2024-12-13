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

test-files:
	./build/install/app/bin/app file1.json file2.json

.PHONY: build clean lint config test



