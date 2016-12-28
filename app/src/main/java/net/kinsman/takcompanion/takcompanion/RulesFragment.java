package net.kinsman.takcompanion.takcompanion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RulesFragment extends Fragment {

    String rulesText = "<big><b>Rules</b></big><br/>\n" +
            "        <p>As of March 9, 2016, the official rules for Tak are described by Cheapass Games on their website.</p>\n" +
            "\n" +
            "        <big><b>Setup</b></big><br/>\n" +
            "        Tak can be played on several sizes of boards. Depending on the size, players will use the appropriate number of stones. All Tak games start with an empty board. <br/>\n" +
            "\n" +
            "        <br/><table border = \"1\" align=\"center\">" +
            "        <tr> " +
            "        <th>Board size</th>" +
            "        <td>3x3</td>" +
            "        <td>4x4</td>" +
            "        <td>5x5</td>" +
            "        <td>6x6</td>" +
            "        <td>7x7</td>" +
            "        <td>8x8</td>" +
            "        </tr>" +
            "        <tr> " +
            "        <th>Normal pieces</th>" +
            "        <td>10</td>" +
            "        <td>15</td>" +
            "        <td>21</td>" +
            "        <td>30</td>" +
            "        <td>40</td>" +
            "        <td>50</td>" +
            "        </tr>" +
            "        <tr>" +
            "        <th>Capstone</th>" +
            "        <td>0</td>" +
            "        <td>0</td>" +
            "        <td>1</td>" +
            "        <td>1</td>" +
            "        <td>1-2</td>" +
            "        <td>2</td>" +
            "        </tr>" +
            "        </table><br/>" +
            "        <p>Tak, similar to Chess and Checkers, can be played on a square board with alternating \"light\"\n" +
            "            and \"dark squares\". In addition, Cheapass Games released a specialized hybrid board to\n" +
            "            provide a single surface for 6x6, 5x5, 4x4, and 3x3 games. For odd-sized games, pieces\n" +
            "            are played on the squares (similar to a Chess board.) Even-sized games are played on the\n" +
            "            small diamonds located at the corners and intersections of the squares (similar to a Go board.)\n" +
            "            If there is no board available, players may use an object or a temporary marker to designate\n" +
            "            the center of the board. Players must imagine the rest of the board until there are enough\n" +
            "            pieces in play to define it. The object or marker will then be removed.</p>\n" +
            "\n" +
            "        <p>Also similar to the conventions of chess, checkers, and Go, Tak game pieces, commonly referred\n" +
            "            to as \"stones\", are divided into white and black sets and players are often referred to\n" +
            "            \"White\" and \"Black\" respectively. Tak sets, however, can come in a variety of colors and\n" +
            "            styles. The capstone can be of any shape, and the stones should be simple, stackable\n" +
            "            pieces in a matching style.</p>\n" +
            "\n" +
            "        <i><p>For a game played on a 7x7 board, the number of capstones is determined by player agreement.</p></i>\n" +
            "\n" +
            "        <big><b>First turn</b></big><br/>\n" +
            "        <p>On each player\\'s first turn, they must place one of their opponent\\'s pieces on any empty\n" +
            "            space on the board. The piece must be a flat stone of their opponent\\'s color. Play then proceeds normally with players controlling their own pieces.\n" +
            "\n" +
            "        <p>Players determine randomly who starts the first game, and alternate the first move for\n" +
            "            future games. In competitive play, white plays first.</p>\n" +
            "\n" +
            "        <big><b>Each turn</b></big>\n" +
            "        <p>After the first turn, players may make the choice during their turn to either place a stone\n" +
            "            or move stones under their control. There is no option to pass a turn.</p>\n" +
            "\n" +
            "        <b>Placement</b>\n" +
            "        <p>During their turn, players may place one stone from their reserve onto an empty spot on the\n" +
            "            board. There are three stone types that may be placed:\n" +
            "\n" +
            "        <li>Flat stone: Normal stones played flat. Flat stones can be stacked upon, and they count as part of a road.</li>\n" +
            "        <li>Standing stone: Normal stones played on their edge. Nothing can be stacked upon a standing\n" +
            "            stone, but they do not count as part of a road. Also commonly called a \"wall\".</li>\n" +
            "        <li>Capstone: The most powerful piece, as they count towards a road and cannot be stacked upon.\n" +
            "            The capstone also has the ability to move by itself onto a standing stone and flatten the\n" +
            "            standing stone into a flat stone. An opponent\\'s standing stones and a player\\'s own standing\n" +
            "            stones can be flattened in this manner.</li></p>\n" +
            "\n" +
            "        <b>Movement</b>\n" +
            "        <p>A player may move a single piece or a stack of pieces they control. The stone on top of a\n" +
            "            stack determines which player has control of that entire stack. All stones move in a\n" +
            "            straight line on the board. There is no diagonal movement, and all stones must proceed forward across the board.</p>\n" +
            "\n" +
            "        <p>Moving stones is the only way to make stacks. As a stack moves, the player has the option of\n" +
            "            breaking the stack, covering any existing flat stones along the way. Each space must have\n" +
            "            one or more stones placed on each space as it moves, but a player has the option to leave\n" +
            "            zero or more pieces on the starting space. There is no height limit for stacks, but all\n" +
            "            stacks must be below the carry limit set by the board size in order to leave no stones\n" +
            "            on the starting space. For example, if the stack was on a 5x5, the carry limit of a stack is 5.</p>\n" +
            "\n" +
            "        <p>Standing stones and capstones cannot have any stone stack on top of it. Any move that would\n" +
            "            place a stone atop a standing stone or capstone is not legal. The only exception to this\n" +
            "            is when a capstone moves by itself onto a standing stone, flattening it. A capstone may\n" +
            "            make a longer move with a taller stack to flatten a standing stone, but it must be the\n" +
            "            only piece that moves onto the standing stone.</p>\n" +
            "\n" +
            "        <big><b>End of game</b></big>\n" +
            "        <p>The primary goal of Tak is to build a road from one opposite end of the board to the other.\n" +
            "            Only flat stones and capstones can contribute to a road, while standing stones do not. As\n" +
            "            soon as the road is built, the player who built it wins. This is called a \"road win\".\n" +
            "            Roads do not have to be in a straight line, but stones can only connect when they are\n" +
            "            adjacent to one another. Stones cannot connect diagonally.</p>\n" +
            "\n" +
            "        <p>If a player makes a move that results in a winning road for both players, the active player wins.</p>\n" +
            "\n" +
            "        <p>If a road is not built by either player, a player can also win by controlling the most spaces\n" +
            "            with flat stones on the board. The game will end when a player places their last piece,\n" +
            "            or when all spaces on the board are covered. The player with the most flat stones wins.\n" +
            "            Standing stones and capstones do not count. Stones captured by other pieces also do not\n" +
            "            count, only the flat stone on top.</p>";

    public RulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rules, container, false);
        WebView rules = (WebView)view.findViewById(R.id.wvRulesText);
        rules.loadDataWithBaseURL(null, rulesText, "text/html", "utf-8", null);
        return view;
    }

}
