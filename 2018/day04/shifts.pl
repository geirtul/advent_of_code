#!/usr/bin/perl
use strict;
use warnings;

use DateTime;
use Data::Dumper;
use List::UtilsBy qw(max_by);

my $file = "input.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";


# =========== Solve the puzzle ============
# Part 1:
@lines = sort(@lines);
my $sleeps = calc_sleeptimes(@lines);
my $totals = total_sleep($sleeps);
my $sleepy_guard = max_by {%$totals{$_}} keys(%$totals);
my ($most_frequent, $freq) = &most_frequent_sleeptime($sleeps, $sleepy_guard);
my $result = $sleepy_guard*$most_frequent;
print "The guard sleeping the most is: $sleepy_guard\n";
print "The minute he sleeps the most is: $most_frequent\n";
print "The result is: $result with $freq times.\n";

# Part 2:
my $steady_guard;
my $highest_freq = 0;
my $steady_minute;
foreach my $guard (keys(%$sleeps)) {
  my ($minute, $freq) = &most_frequent_sleeptime($sleeps, $guard);
  if ($freq > $highest_freq) {
    $steady_minute = $minute;
    $highest_freq = $freq;
    $steady_guard = $guard;
  }
}
my $result2 = $steady_guard*$steady_minute;
print "The guard sleeping on a minute the most is: $steady_guard\n";
print "The minute and number of times are: $steady_minute , $highest_freq\n";
print "The result is: $result2\n";

# =========== Subroutine declarations ============
sub most_frequent_sleeptime {
  # Based on sleep/wake time of the most sleepy guard,
  # find the minute(s) the guard is most often asleep.
  my $aref = shift;
  my $guard = shift;

  my %frequencies;
  my @times = @{$aref->{$guard}};
  my $i = 0;
  while ($i < scalar(@times)) {
      foreach $_ ($times[$i]..($times[$i+1]-1)) {
        $frequencies{$_}++;
      }
      $i += 2;
  }
  my $most_frequent = max_by {$frequencies{$_}} keys(%frequencies);
  my $freq = $frequencies{$most_frequent};
  return ($most_frequent, $freq);
}

sub total_sleep {
  # Calculate the total amount of sleep for an agent
  # based on sleep/wake times in sleeptimes hash.
  my $href = shift; # hash reference
  #print Dumper($aref);
  my %total_times;
  foreach my $key (keys(%$href)) {
    my @times = @{$href->{$key}};
    my $sum = 0;
    my $i = 0;
    # Know times come in pairs of sleep, wake.
    # Ex. sleep = 1, wake = 4, 4-1 = 3 minutes of sleep.
    while ($i < scalar(@times)) {
        $sum += $times[$i+1] - $times[$i];
        $i += 2;
    }
    $total_times{$key} = $sum;
  }
  return \%total_times
}

sub calc_sleeptimes {
  # Create a hash with Guard IDs as keys, and sleeping start/stop as values
  # by storing a reference to the array containing it.
  my @in = @_;
  # Match datetime, what happens, and guard ID.
  my $regex = qr/\[.+:(\d\d)\]\s(Guard|falls|wakes)\s#?(\d+|asleep|up)/;
  my $current_id;
  my %sleeptimes;
  foreach $_ (@in) {
    my @match = $_ =~ $regex;
    my $time = $match[0];
    # If it's a guard event, set current id.
    if ($match[1] eq "Guard") {
      $current_id = $match[2];
      next; # Just need the ID, move on to data.
    }
    # if it's a sleep-even, append sleeptime.
    if ($match[1] eq "falls"){
      # Check if id exists in hash
      if (exists($sleeptimes{$current_id})) {
        push(@{$sleeptimes{$current_id}}, $time);
      } else {
        my @arr = $time;
        $sleeptimes{$current_id} = \@arr;
      }
    }
    # If it's a wakes-event, append waketime
    if ($match[1] eq "wakes"){
      if (exists($sleeptimes{$current_id})) {
        push(@{$sleeptimes{$current_id}}, $time);
      } else {
        my @arr = $time;
        $sleeptimes{$current_id} = \@arr;
      }
    }
  }
  return \%sleeptimes;
}
