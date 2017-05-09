import aiproj.slider.Move;

import java.util.ArrayList;

/**
 * Created by daewi on 0001, May, 1.
 */
public abstract class SearchStrategy {

    abstract Move getBestMove(SliderBoard board);

    /**
     * Get a list of all possible moves that our player can make at this board state
     * @param board
     * @return
     */
    public static ArrayList<Move> getAllPossibleMoves(SliderBoard board, SliderBoardPiece.PieceType playerType){

        ArrayList<Move> allMoves = new ArrayList<>();

        // Filter all our pieces based on the player type, and get each of their possible
        // moves.
        for(SliderBoardPiece piece : board.hashPieces.values()){
            if(piece.type.equals(playerType)){
                allMoves.addAll(getNextPossibleMovesByPiece(board, piece));
            }
        }

        return allMoves;
    }

    /**
     * Get a list of possible moves for a particular piece on this board
     * @param board
     * @param piece
     * @return
     */
    public static ArrayList<Move> getNextPossibleMovesByPiece(SliderBoard board, SliderBoardPiece piece){

        ArrayList<Move> moves = new ArrayList<>();

        int iPosition = piece.position.i;
        int jPosition = piece.position.j;

        if(PieceMovement.canMoveUp(board, piece)){
            moves.add(new Move(iPosition, jPosition, Move.Direction.UP));
        }

        if(PieceMovement.canMoveDown(board, piece)){
            moves.add(new Move(iPosition, jPosition, Move.Direction.DOWN));
        }

        if(PieceMovement.canMoveLeft(board, piece)){
            moves.add(new Move(iPosition, jPosition, Move.Direction.LEFT));
        }

        if(PieceMovement.canMoveRight(board, piece)){
            moves.add(new Move(iPosition, jPosition, Move.Direction.RIGHT));
        }

        return moves;
    }


    /**
     *
     * @param board
     * @return
     */
    public static int getNormalizedEvaluation(SliderBoard board, ArrayList<Feature> evaluationFunction){

        int evaluationValue = 0;

        for(Feature feature : evaluationFunction){
            evaluationValue += feature.evaluate(board);
        }

        return evaluationValue;
    }

}
