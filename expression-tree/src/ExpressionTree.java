/**
 * @author Jeremy Corren [jdc2189]
 * 
 * The ExpressionTree class implements a binary expression tree.
 * Methods convert a user-supplied 
 */

import java.io.*;

public class ExpressionTree<T> {

	@SuppressWarnings("rawtypes")
	private static ArrayStack<ExpressionTree> myStack= new ArrayStack<>(); 	// stack in which expression tree is stored
    protected TreeNode<T> root;												// root of the expression tree
    
    /**
     * Represents a subtree of ExpressionTree.
     */
    protected static class TreeNode<T> {

        public T data;  		  // the data 
        public TreeNode<T> left;  // left subtree
        public TreeNode<T> right; // right subtree
        
        public TreeNode(T theData, TreeNode<T> leftChild, TreeNode<T> rightChild) {
            data = theData; 
            left = leftChild;
            right = rightChild;
        }
        
        public void printTree(int indent) {
            for (int i=0;i<indent;i++)
                System.out.print("..");
            
            System.out.println(data);        // Node
            if(left != null)
                left.printTree(indent + 1);  // Left
            if(right != null)
                right.printTree(indent + 1); // Right
        }
        
        /**
         * Recursively appends data of ExpressionTree nodes to a 
         * StringBuilder object to generate an infix expression.
         * 
         * @param preExp an empty StringBuilder object
         */
		private void toPrefixRec(StringBuilder preExp) {
			preExp.append(data + " ");
			if(left != null)
				left.toPrefixRec(preExp);
			
			if(right != null)
				right.toPrefixRec(preExp);
		}
		
		/**
         * Recursively appends data of ExpressionTree nodes to a 
         * StringBuilder object to generate an infix expression.
         * 
         * NOTE: The method produces parentheses around each individual
         * operand as well as each operand expression, so instead of the
         * cleaner '(1 + (3 + 2) * 7) / 2', this method produces the
         * output '((1) + (((3) + (2)) * (7))) / (2).' More importantly, 
         * the expressions give the same result when evaluated, 18.
         * 
         * @param inExp an empty StringBuilder object
         */
		public void toInfixRec(StringBuilder inExp) {
			
			if(left == null) {
				inExp.append(data); // operand
			}
			else {
				inExp.append("(");
				left.toInfixRec(inExp);
				inExp.append(")");
				
				inExp.append(" " + data + " "); // operator
				
				inExp.append("(");
				right.toInfixRec(inExp);
				inExp.append(")");
			}		
		}
		
		/**
		 * Recursively evaluates subtrees of the ExpressionTree and
		 * calls calculate() to perform the computation.
		 * 
		 * @return calls calculate() on the recursively obtained operands
		 * and operator to solve each subproblem
		 */
		private int evaluate() {
			if(left == null)
				return (int) data;
			else {
				int leftOp = left.evaluate();
				int rightOp = right.evaluate();
				return calculate(leftOp, (char) this.data, rightOp);
			}
		}
		
		/**
		 * 
		 * @param op1 left operand
		 * @param opt the operator
		 * @param op2 right operand
		 * @return rslt the result of computation
		 */
		private int calculate(int op1, char opt, int op2) {
			int rslt = 0;
			
			if(opt == '+')
				rslt = op1 + op2;
			else if(opt == '-')
				rslt = op1 - op2;
			else if(opt == '*')
				rslt = op1 * op2;
			else if(opt == '/')
				rslt = op1 / op2;
			return rslt;
		}
    }
    // end of tree node class
    
    public ExpressionTree() {
    	root = null;
    }
    
    public ExpressionTree(TreeNode<T> rootNode) {
        root = rootNode;
    }
    
    /**
     * Converts user input from post-fix to prefix notation by calling
     * a private method toPrefixRec() in the TreeNode class.
     * 
     * @return prefixExpression the expression in prefix notation
     */
    public String toPrefix() {
    	StringBuilder prefixExpression = new StringBuilder();
    	
    	if(root != null)
    		root.toPrefixRec(prefixExpression);
    	
    	String prefixString = prefixExpression.toString();
    	return prefixString;
    }
    
    /**
     * Converts user input from post-fix to infix notation by calling
     * a private method toInfixRec() in the TreeNode class.
     * 
     * @return infixExpression the expression in infix notation
     */
    public String toInfix() {
    	StringBuilder infixExpression = new StringBuilder();
    	
    	if(root != null)
    		root.toInfixRec(infixExpression);
    	
    	String infixString = infixExpression.toString();
    	return infixString;
    }
    
    /**
     * Evaluates the expression contained in the expression tree,
     * calls a private method evaluate() in the TreeNode class.
     * 
	 * @return result of evaluated expression
	 */
    public float evaluate() {
    	float result = 0;
    	
    	if(root != null)
    		result = root.evaluate();
        	
    	return result;
    }
    
    public void printTree() {
        if(root != null)
            root.printTree(0);
        else
            System.out.println("Empty Tree");
    }
    
    /**
     * Creates an expression tree from user input by pushing operands as
     * single-node trees into a stack and then popping them as children when
     * operators are encountered to form expression subtrees.
     * 
     * @param input a post-fix expression from the user
     * @return an instance of ExpressionTree that represents the full tree
     */
    @SuppressWarnings("unchecked")
	public static <T> ExpressionTree<T> makeExpressionTree(String input) {		
		String[] sArray = input.split("\\s+");
		
		for(int i = 0; i < sArray.length; i++) {
			if(sArray[i].matches("-?\\d+")) {
				Integer operand = Integer.parseInt(sArray[i]);
				
				ExpressionTree<T> opndTree = (ExpressionTree<T>) etree(operand);
				myStack.push(opndTree);
			}
			else if(checkOperator(sArray[i]) == true) {
				Character operator = sArray[i].charAt(0);
				
				ExpressionTree<T> right = myStack.pop();
				ExpressionTree<T> left = myStack.pop();
				ExpressionTree<T> optrTree = etree(operator, left, right);
				myStack.push(optrTree);
			}
		}
		return myStack.pop();
	}
	
    /**
	 * Determines whether a character from user-suppled string input
	 * is a binary operator.
	 * 
	 * @return true when argument is an operator, false when it isn't
	 */
	public static boolean checkOperator(String op) {
		if(op.equals("+") || op.equals("-") || op.equals ("*") || op.equals ("/"))
			return true;
		else
			return false;	
	}
	
	/**
	 * @return a new expression tree with two subtrees.
	 */
	@SuppressWarnings("unchecked")
	public static <T> ExpressionTree<T> etree(Character operator, ExpressionTree<T> left, ExpressionTree<T> right) {
        TreeNode<T> newRoot = new TreeNode<T>((T) operator, left.root, right.root);
        return new ExpressionTree<T>(newRoot);
    }
	
	/**
	 * @return a new expression tree with no children.
	 */
    public static <T> ExpressionTree<T> etree(T theItem) {
        return new ExpressionTree<T>(new TreeNode<T>(theItem, null, null));
    }

    /**
     * Tester class for ExpressionTree.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	
    	System.out.println("*******************************************");
    	System.out.println("ExpressionTree Builder & Notation Converter");
    	System.out.println("*******************************************");
    	
    	System.out.print("Type an expression: ");
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String postfixExpression = null;
        
        postfixExpression = br.readLine();
       
        ExpressionTree<?> e = makeExpressionTree(postfixExpression);
        System.out.println();
        
        System.out.println("Prefix: " + e.toPrefix());
        System.out.println("Infix: " + e.toInfix());
        System.out.println("Result: " + e.evaluate());
    }
}