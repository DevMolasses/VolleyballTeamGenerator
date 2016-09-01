# Volleyball Team Generator
This program will randomly generate volleyball teams. The number of teams will be based on 6-person teams with no more than 8 on a team. All the males in play will be evenly distributed among the teams and the teams with the fewest males will be the first teams to get extra females.

#Background
My wife and I play pick-up games of volleyball at my church once a week. One of the largest challenges of playing is selecting teams. Many methods were attempted with questionable results; we tried holding 1 or 2 fingers behind our back and all revealing at once, we tried dividing by birthdays, we tried to split the players based on skill, as well as several other methods of selection. All attempts led to players feeling left out, players "cheating" the system to be on the team with their buddy, and often the teams were uneven in ability which leads to only half the people enjoying themselves.

In order to solve this problem, I decided to create a program that allows players to sign in as the arrive. Once all players are signed in, teams are generated randomly so no one can dispute the results.

#Project Highlight
The biggest challenge of generating teams randomly is that the number of players changed each week. As a result, the number of teams and the number of players on each team varied as well. Additionally, the spread of males and females needed to be even across all the teams to spread the skills and abilities more fairly.

To overcome this challenge, I developed an algorithm that would determine the number of teams based on the number of players, never allowing more than 7 players per team (we would prefer to make three teams of 5 instead of having to rotate 2 people out during play). Once the number of teams was determined, the males are selected randomly and placed into teams in ascending order (team 1, team 2 etc...). If the males did not distribute perfectly amongst the teams (i.e. team 3 has fewer males than teams 1 & 2), random females are selected to make all the teams have an equal number of player. After all the teams are equal in size, the females are selected randomly and placed into teams in descending order (team 3, team 2, etc...). Assigning teams this way ensured that if a team had fewer males, it would not also have fewer total players.

The algorithm that determines the number of teams starts on line 296 of TeamGenerator.java.
The algorithm that places the players on the appropriate teams starts on line 333 of TeamGenerator.java.