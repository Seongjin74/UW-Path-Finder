# UW Path Finder

## 📌 Overview (개요)
**UW Path Finder** is a Java-based application that finds the **shortest walking path** between buildings on the **University of Wisconsin-Madison** campus.  
It utilizes **Dijkstra's Algorithm** to compute the optimal route using data from a **DOT file**.

**UW Path Finder**는 **위스콘신 대학교 매디슨 캠퍼스** 내 **건물 간 최단 경로**를 찾는 Java 기반 애플리케이션입니다.  
**다익스트라 알고리즘(Dijkstra’s Algorithm)**을 사용하여 **DOT 파일**에서 제공되는 데이터로 최적의 경로를 계산합니다.

---

## 🛠️ Features (주요 기능)  
- 📂 **Load Data File**: Reads a **DOT file** containing campus map data (nodes and edges).  
  - **데이터 파일 로드**: 캠퍼스 맵 정보(노드 및 엣지)가 포함된 **DOT 파일을 읽어옴**  
- 📊 **Display Dataset Statistics**: Shows the number of buildings (nodes), paths (edges), and the total walking time.  
  - **데이터셋 통계 출력**: **건물 개수(노드), 경로 개수(엣지), 총 이동 시간**을 표시  
- 🗺️ **Find Shortest Path**: Computes the shortest walking route between two buildings using **Dijkstra’s Algorithm**.  
  - **최단 경로 탐색**: **다익스트라 알고리즘**을 사용하여 두 건물 간 **최단 도보 경로**를 계산  
- 🚪 **Exit Application**: Closes the program.  
  - **프로그램 종료**: 애플리케이션을 종료  

---

## 💻 Installation & Usage (설치 및 사용법)
### **1️⃣ Clone the repository (레포지토리 클론)**
```bash
git clone https://github.com/Seongjin74/UW-Path-Finder.git
cd UW-Path-Finder
```

### **2️⃣ Compile the Java files (Java 파일 컴파일)**
```bash
javac $(ls *.java | grep -v "Test") 
```

### **3️⃣ Run the application (프로그램 실행)**
```bash
java Frontend
```

---

## 🏃‍♂️ How to Use (사용 방법)
When you start the application, the following menu will be displayed:  
애플리케이션을 실행하면 다음과 같은 메뉴가 표시됩니다.

```
Select a command:
1. Load Data File
2. Display Dataset Statistics
3. Find Shortest Path
4. Exit
```

### **🗂️ Example Usage (사용 예시)**

#### **📌 Load a data file (데이터 파일 불러오기)**
```bash
1
Enter the name of the data file:
campus.dot
Data file loaded successfully.
```

#### **📊 Display dataset statistics (데이터셋 통계 출력)**
```bash
2
Number of nodes: 160
Number of edges: 800
Total walking time: 110675.5 seconds
```

#### **🗺️ Find the shortest path (최단 경로 찾기)**
```bash
3
Enter start building:
Radio Hall
Enter destination building:
Computer Sciences and Statistics

Shortest path from Radio Hall to Computer Sciences and Statistics:
Radio Hall -> Education Building : 113.0 seconds
Education Building -> South Hall : 187.6 seconds
South Hall -> Law Building : 112.8 seconds
Law Building -> X01 : 174.7 seconds
X01 -> Luther Memorial Church : 65.5 seconds
Luther Memorial Church -> Noland Hall : 183.5 seconds
Noland Hall -> Meiklejohn House : 124.2 seconds
Meiklejohn House -> Computer Sciences and Statistics : 164.2 seconds
Total walk time: 1125.5 seconds
```

#### **🚪 Exit the application (프로그램 종료)**
```bash
4
Exiting application...
```

---

## 🛠️ Technologies Used (사용된 기술)
- **Java**: Primary programming language (주 프로그래밍 언어)
- **Dijkstra’s Algorithm**: Used to compute the shortest path (최단 경로 탐색을 위한 다익스트라 알고리즘)
- **DOT File Parsing**: Reads and processes `.dot` files for campus map data (DOT 파일을 읽어 캠퍼스 맵 데이터를 처리)

---

## 📂 Project Structure (프로젝트 구조)
```
📂 UW-Path-Finder
├── 📜 Backend.java               # Core logic for processing map data & pathfinding
├── 📜 BackendInterface.java      # Interface defining backend functionalities
├── 📜 Frontend.java              # User interface for interacting with the backend
├── 📜 FrontendInterface.java     # Interface defining frontend functionalities
├── 📜 DijkstraGraph.java         # Implementation of Dijkstra’s Algorithm
├── 📜 GraphADT.java              # Graph data structure for map representation
├── 📜 PlaceholderMap.java        # Placeholder graph implementation
├── 📜 campus.dot                 # Example campus map data file
├── 📜 README.md                  # Project documentation (현재 파일)
└── 📜 Makefile                   # Compilation automation (optional)
```

---

## 👤 Author (작성자)
👤 **Seongjin Park**  
📧 Email: seongjinpark99@gmail.com  
🏫 University of Wisconsin-Madison  
