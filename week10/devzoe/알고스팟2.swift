//큐
class Queue {
    var elements: [[Int]] = []
    var head: Int = 0
    
    func append(_ element: [Int]) {
        elements.append(element)
    }
    
    func popleft() -> [Int]? {
        if head >= elements.count {
            return nil
        }
        
        let element = elements[head]
        
        head += 1

        if head > 50 {
            elements.removeFirst(head)
            head = 0
        }
        
        return element
    }
    
    var isEmpty: Bool {
        return head >= elements.count
    }
}

func readInput() -> (Int, Int, [[Int]]) {
    let line = readLine()!.split(separator: " ").map { Int($0)! }
    let ny = line[0], nx = line[1]
    var map: [[Int]] = []
    for _ in 0..<nx {
        let line = readLine()!.map { Int(String($0))! }
        map.append(line)
    }
    return (nx, ny, map)
}
func isValid(_ nx: Int, _ ny: Int, _ x: Int, _ y: Int) -> Bool {
    if 0 <= x && x < nx && 0 <= y && y < ny {
        return true
    }
    return false
}

func bfs(_ nx: Int, _ ny: Int, _ map: [[Int]]) {
    let direction = [[0,1], [0,-1], [1,0], [-1,0]]
    var count: [[Int]] = [[Int]](repeating: [Int](repeating: Int.max, count: ny), count: nx)
    let queue = Queue()
    queue.append([0,0])
    count[0][0] = 0
    while !queue.isEmpty {
        let now = queue.popleft()!
        for d in direction {
            let nextX = now[0]+d[0]
            let nextY = now[1]+d[1]
            if isValid(nx, ny, nextX, nextY) {
                if map[nextX][nextY] == 1 {
                    if count[nextX][nextY] > count[now[0]][now[1]] + 1 {
                        count[nextX][nextY] = count[now[0]][now[1]] + 1
                        queue.append([nextX, nextY])
                    }
                } else {
                    if count[nextX][nextY] > count[now[0]][now[1]] {
                        count[nextX][nextY] = count[now[0]][now[1]]
                        queue.append([nextX, nextY])
                    }
                }
            }
        }
    }
    print(count[nx-1][ny-1])

}
func main() {
    let (nx, ny, map) = readInput()
    bfs(nx,ny,map)
}
main()