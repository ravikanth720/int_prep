package com.github.ravikanth720.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

class SurroundedRegions {

    public static void main(String[] args) {
        SurroundedRegions s = new SurroundedRegions();
        char[][] c = new char[][] {new char[] {'O', 'O', 'O'}, new char[] {'O', 'O', 'O'}, new char[] {'O', 'O', 'O'}};
        s.solve(c);
        System.out.println(c);
    }

    public void solve(char[][] board) {
      if (board.length == 0 || board[0].length == 0) return;

      Deque<Position> q = new ArrayDeque<>();
      Set<String> added = new HashSet<>();
      q.offerLast(new Position(0, 0));

      while(!q.isEmpty()) {
          Position curr = q.pollFirst();
          if (board[curr.i][curr.j] == 'O') {
              board[curr.i][curr.j] = dfs(board, curr.i, curr.j);
          }

          if (curr.i < board.length-1) {
              if (added.add((curr.i + 1) + "-" + curr.j)) {
                  q.offerLast(new Position(curr.i + 1, curr.j));
              }
          }

          if (curr.j < board[0].length-1) {
              if (added.add(curr.i + "-" + (curr.j+1))) {
                  q.offerLast(new Position(curr.i, curr.j + 1));
              }
          }
      }
    }

    public char dfs(char[][] board, int i, int j) {
      if (i >= board.length || i < 0 || j >= board[0].length || j < 0) return 'O';
      if (board[i][j] != 'O') return 'X';

      board[i][j] = 'Y';

      char resL = dfs(board, i, j-1);
      char resR = dfs(board, i, j+1);
      char resU = dfs(board, i-1, j);
      char resD = dfs(board, i+1, j);

      board[i][j] = (resL == 'O' || resR == 'O' || resU == 'O' || resD == 'O') ? 'O' : 'X';
      return board[i][j];
    }

    class Position {
      int i, j;
      Position(int i, int j) {
          this.i = i;
          this.j = j;
      }
    }
}