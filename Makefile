all: runBDTests runFDTests

compile: MapADT.java ShortestPath.java TextUITester.java FrontendDeveloperTests.java PlaceholderMap.java GraphADT.java BaseGraph.java BackendPlaceholder.java Backend.java Frontend.java
	
	javac -cp .:../junit5.jar FrontendDeveloperTests.java

runFDTests: compile
	java -cp ".:../*" org.junit.platform.console.ConsoleLauncher --select-class FrontendDeveloperTests


run: compile
	java Frontend



runBDTests: BackendDeveloperTests.class
	java -jar ../junit5.jar --class-path=. --select-class=BackendDeveloperTests


BackendDeveloperTests.class:
	javac -cp ../junit5.jar:. BackendDeveloperTests.java



clean:
	rm -f *.class
