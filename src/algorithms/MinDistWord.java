class MinDisWord {
  /**
   * Find min transformations to convert start to end
   * Ex: start = 'pig', end = 'sun', bag = ['ban', 'pin', 'bun', 'pun', 'bin']
   */

  public int getPriority(String code) {
    if (code.equals("A")) return 3;
    if(code.equals("C")) return 2;
    return 1;
  }

  public void orderProductsByPriority(String[] productCodes) {
    int mid = 0, low = 0, high = productCodes.length - 1;
    while(mid <= high) {
      int priority = getPriority(productCodes[mid]);
      if (priority == 1) {
        swap(productCodes, mid, low);
        mid++;
        low++;
      } else if (priority == 2) {
        mid++;
      } else {
        swap(productCodes, mid, high);
        high--;
      }
    }
  }

  private void swap(String[] arr, int i, int j) {
    String temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    MinDisWord m = new MinDisWord();
    String[] pc = new String[]{"A", "B", "C"};
    m.orderProductsByPriority(pc);
    for(String c: pc) {
      System.out.println(c);
    }
  }
}