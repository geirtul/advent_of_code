#!/usr/bin/perl
use strict;
use warnings;

use Data::Dumper;
use List::Util qw(max min any none);
use List::UtilsBy qw(max_by);
use List::MoreUtils qw(first_index);
use Algorithm::Dependency::Ordered;

# File import
my $file = "input.txt";
open my $input, '<', $file or die "can't open $file: $!";
chomp(my @lines = <$input>);
close $input or die "can't close $file: $!";

# Preparation
my @letters;
foreach $_ (@lines) {
  my @rule = extract_rule($_);
  push (@letters, $rule[0]) if not any {$_ eq $rule[0]} @letters;
  push (@letters, $rule[1]) if not any {$_ eq $rule[1]} @letters;
}
## Create a hash containing key: task and values: tasks depending on key.
my $NUM_TASKS = scalar(@letters);

my %dependencies;
foreach $_ (@lines) {
  my @rule = extract_rule($_);
  if (exists($dependencies{$rule[1]})) {
    push(@{$dependencies{$rule[1]}}, $rule[0]);
  } else {
    my @arr = ($rule[0]);
    $dependencies{$rule[1]} = \@arr;
  }
}
my %dependents;
foreach $_ (@lines) {
  my @rule = extract_rule($_);
  if (exists($dependents{$rule[0]})) {
    push(@{$dependents{$rule[0]}}, $rule[1]);
  } else {
    my @arr = ($rule[1]);
    $dependents{$rule[0]} = \@arr;
  }
}

# Solve the puzzle
## Part 1:
my @queue;
my @finished;
my @first = get_first();
queue(@first);
runner($queue[0]);
print "Part 1:\n";
print @finished;

## Part 2:  # 1117 too high.
            # 215 too low.
print "\nPart 2:\n";
@queue = (); # empty the queue and finished.
@finished = ();
queue(@first);
runner_multiple();

# Subroutine declarations

sub runner {
  # Runs the queuing operations and starts input task.
  my $current = shift;
  push (@finished, $current) if (not any {$_ eq $current} @finished);
  my @dependents = get_dependencies($current);
  # Check if the dependencies are eligible for queue.
  my @eligible;
  foreach my $task (@dependents) {
    if (check_eligibility($task)) {
      push(@eligible, $task);
    }
  }
  queue(@eligible);
  my $next = shift @queue;
  runner($next) if $next;
}

sub runner_multiple {
  # Runs the queuing operations and starts input task.
  # Adapted for having multiple workers.
  my $iter = 0;
  my $available_workers = 5;
  my %in_progress;
  while (1) {
    # reduce duration left on all tasks in progress.
    if (%in_progress) {
      foreach $_ (keys %in_progress) {
        $in_progress{$_}--;
      }
    }
    # Check if any tasks are done. If so, push the task to @finished and
    # make one worker available. Also queue eligible tasks.
    foreach my $key (sort(keys %in_progress)) {
      if ($in_progress{$key} == 0){
        push(@finished, $key);
        my @dependents = get_dependencies($key);
        # Check if the dependencies are eligible for queue.
        my @eligible;
        foreach my $task (@dependents) {
          if (check_eligibility($task)) {
            push(@eligible, $task);
          }
        }
        queue(@eligible);
        $available_workers++;
      }
    }
    # Take available tasks and start them if there are workers available and
    # tasks queued.
    while ((scalar(@queue) > 0) and ($available_workers > 0)) {
      my $key = shift @queue;
      my $duration = ord($key) - 4; # ord(A) = 65 -> subtract 64.
      $in_progress{$key} = $duration;
      $available_workers--;
    }
    if (scalar(@finished) == $NUM_TASKS){
      print "Finished after $iter time units\n";
      print @finished;
      last;
    }
    $iter++;
  }
}

sub queue {
  # Queue eligible tasks to be completed, and sort the queue alphabetically.
  foreach my $task (@_){
    if ((not any {$_ eq $task} @queue) and (not any {$_ eq $task} @finished)) {
      push(@queue, $task);
    }
  @queue = sort @queue;
  }
}

sub check_eligibility {
  # Check if all dependencies are met for a task.
  my $dependent = shift;
  foreach my $task (@{$dependencies{$dependent}}) {
    if (none {$_ eq $task} @finished){
      return 0; # Not eligible
    }
  }
  return 1; # Eligible
}

sub get_first {
  # Find the tasks which do not depend on any other tasks.
  my @firsts;
  foreach my $c (@letters) {
    push(@firsts, $c) if not exists($dependencies{$c});
  }
  @firsts = sort @firsts;
  return @firsts;
}

sub get_dependencies {
  # Get the tasks which are dependent on the input task.
  my $current = shift;
  my @deps;
  foreach my $key (keys %dependencies) {
    if (any {$_ eq $current} @{$dependencies{$key}}) {
      push(@deps, $key);
    }
  }
  return sort @deps;
}

sub extract_rule {
  # Regex to filter away everything by the task letters.
  my $in = shift;
  my @events = $in =~ /Step ([A-Z]) must be finished before step ([A-Z])/g;
  return @events;
}
