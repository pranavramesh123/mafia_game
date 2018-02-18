/*
 *  Copyright (C) 2015 mafiagame.ir
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package co.mafiagame.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Esa Hekmatizadeh
 */
public class GameSetup {
    private final long citizenNo;
    private final long mafiaNo;
    private final boolean hasDetective;
    private final boolean hasDoctor;
    private final Map<String, Role> players = new HashMap<>();

    GameSetup(long citizenNo, long mafiaNo, boolean hasDetective, boolean hasDoctor) {
        this.citizenNo = citizenNo;
        this.mafiaNo = mafiaNo;
        this.hasDetective = hasDetective;
        this.hasDoctor = hasDoctor;
    }


    void setPlayerRole(Player player) {
        players.put(player.getUserId(), player.getRole());
    }

    public Map<String, Role> getPlayers() {
        Map<String, Role> copy = new HashMap<>();
        copy.putAll(players);
        return copy;
    }

    public long getCitizenNo() {
        return citizenNo;
    }

    public long getMafiaNo() {
        return mafiaNo;
    }

    public boolean isHasDetective() {
        return hasDetective;
    }

    public boolean isHasDoctor() {
        return hasDoctor;
    }

    public long totalPlayer() {
        return citizenNo + mafiaNo + (hasDetective ? 1 : 0) + (hasDoctor ? 1 : 0);
    }
}
