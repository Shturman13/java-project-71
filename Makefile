build:
	make -C app build

test:
	make -C app test

lint:
	make -C app lint

report:
	make -C app report

.PHONY: build test lint



